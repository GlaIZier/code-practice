let assert = require('assert');
let co = require('co');
"use strict";

/** My implementations for different functions */

/** My generator implementation */
function GeneratorResult(value, done) {
  this.value = value;
  this.done = done;
}

function Generator(funcArray) {
  if (funcArray === undefined || funcArray === null || funcArray.length === 0)
    throw new Error("Function array is empty!");
  this._funcArray = funcArray;
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

let myGen = new Generator([() => ":", (arg) => arg + "3", (arg) => console.log(arg)]);
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

      // if promise then handle with callback
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


// Todo implement co like function: one which uses my generator