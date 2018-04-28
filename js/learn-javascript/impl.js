let assert = require('assert');
let co = require('co');
"use strict";

/** My implementations for different functions */

/** My generator implementation */
// Abstraction to hold functions for the generator.
function CodeGenerator(funcArray) {
  if (funcArray === undefined || funcArray === null || funcArray.length === 0)
    throw new Error("Function array is empty!");

  this._funcArray = funcArray;
}

Object.defineProperty(CodeGenerator.prototype, "funcArray", {
  get: function funcArray() {
    return this._funcArray;
  }
});


function GeneratorResult(value, done) {
  this.value = value;
  this.done = done;
}

function Generator(codeGenerator) {
  if (!(codeGenerator instanceof CodeGenerator))
    throw new Error("CodeGenerator is needed!");
  this._funcArray = codeGenerator.funcArray;
  this._nextIndex = 0;
}

Generator.prototype.next = function (yielded) {
  if (this._nextIndex === this._funcArray.length)
    throw new Error("Already consumed!");
  let done = (this._funcArray.length - 1) === this._nextIndex;
  let value = this._funcArray[this._nextIndex](yielded);
  this._nextIndex++;
  return new GeneratorResult(value, done);
};

let myGen = new Generator(new CodeGenerator([() => ":", (arg) => arg + "3", (arg) => console.log(arg)]));
let firstNext = myGen.next();
assert.deepEqual(firstNext, {done: false, value: ":"} );
let secondNext = myGen.next(firstNext.value);
assert.deepEqual(secondNext, {done: false, value: ":3"} );
let thirdNext = myGen.next(secondNext.value);
assert.deepEqual(thirdNext, {done: true, value: undefined} );



/** My co implementation */
function sleep(millis) {
  return new Promise(resolve => setTimeout(resolve, millis));
}

let delayAndReturn = (result, delayInMillis) => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(result);
    }, delayInMillis);
  });
};

function* delayAndReturnGenerator(result, millis, count) {
  let final = result;
  for(let i = 0; i < count; i++) {
    final = yield delayAndReturn(result, millis);
  }
  return result;
}

// Closure function to save resolve, reject and generator not to pass it every time as it is the same for all calls
function _ex(resolve, reject, generator, initialArg) {
  return (function _exRecur(yielded) {
    try {
      let newYielded = generator.next(yielded);
      let newYieldedValue = newYielded.value;

      // don't know why after execution of this line (last line) newYieldedValue.then(result) line will be executed
      // one or several more times
      // All processing is done
      if (newYielded.done === true)
        return resolve(newYieldedValue);

      // if promise (only promise has standard method then) then handle with callback
      if (newYieldedValue.then) {
        newYieldedValue
          .then(resolvedNewYieldedValue => _exRecur(resolvedNewYieldedValue))
          .catch(error => {
            throw error
          });
      } else {
        return _exRecur(newYieldedValue);
      }

    } catch (e) {
      reject(e);
    }
  })(initialArg);
}

function ex(generator) {
  return new Promise((resolve, reject) => {
    _ex(resolve, reject, generator());
  });
}

function exActivated(activatedGenerator) {
  return new Promise((resolve, reject) => {
    _ex(resolve, reject, activatedGenerator);
  });
}


function* codeGenerator() {
  let a = yield delayAndReturn("a", 10);

  let ab = yield delayAndReturn(delayAndReturn(a + "b", 10), 10);
  // generator delegation
  let abc = yield* delayAndReturnGenerator(ab + "c", 10, 3);
  let abcd = abc + "d";
  let abcde = yield abcd + "e";

  // just delay here
  yield sleep(500); // this last yield returns done:true without calling next one more time and return.

  return abcde;
}

ex(codeGenerator)
  .then(result => assert.equal(result, "abcde"))
  .catch(reject => console.log(reject));


let codeGeneratorArray = [
  () => {
  console.log(this);
    return delayAndReturn("a", 10);
  },
  // a got from next
  (a) => {
    return delayAndReturn(delayAndReturn(a + "b", 10), 10);
  },
  // composition of generators doesn't work as it needs more complicated logic
  (ab) => delayAndReturn(ab + "c", 10),
  (abc) => {
    let abcd = abc + "d";
    // save abcde to external this.
    this.abcde = abcd + "e";
    // Todo this = global context. Bad. Avoid it!
    return this.abcde;
  },
  () => sleep(500),
  () => this.abcde
];

let myGenerator = new Generator(new CodeGenerator(codeGeneratorArray));

exActivated(myGenerator)
  .then(result => {assert.equal(result, "abcde"); console.log(this);})
  .catch(reject => console.log(reject));