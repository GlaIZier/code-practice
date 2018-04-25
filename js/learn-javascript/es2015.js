let assert = require('assert');
let co = require('co');
"use strict";

// https://learn.javascript.ru/destructuring
let destructedObject = {
  size: {
    width: 100,
    height: 200
  },
  items: ["donut", "pie"],
  a: "a",
  b: "b"
};
let { title="menu", size: {width, height}, items: [item1, item2]} = destructedObject;
assert.equal(title, "menu");
assert.equal(width, 100);
assert.equal(height, 200);
assert.equal(item1, "donut");
assert.equal(item2, "pie");

let [, cesar = 'unknown' , emperor, ...rome] = "J Cesar Emperor Rome".split(" ");
assert.equal(cesar, "Cesar");
assert.equal(emperor, "Emperor");
assert.deepEqual(rome, ["Rome"]);

// https://learn.javascript.ru/es-function
// Doesn't have this and arguments
function showName(firstName, lastName, ...rest) {
  return rest; // rest is a true array rather than arguments
}
assert.deepEqual(showName("jfjf", "fadf", "rest1", "rest2"), ["rest1", "rest2"]);

let group = {
  title: "Our course",
  students: ["V", "P", "D"],

  showList: function() {
    this.students.forEach(
      student => {
        assert.equal(this.title, "Our course");
        assert.equal(arguments[0], "arg");
      }
    )
  }
};
group.showList("arg");

group = {
  title: "Our course",
  students: ["V", "P", "D"],

  showList: function() {
    this.students.forEach(function(student) {
      assert.equal(this.title, undefined);
      assert.notEqual(this.title, "arg");
    })
  }
};

group.showList("arg");

// https://learn.javascript.ru/es-string
let apples = 2;
let oranges = 3;

assert.equal(`${apples} + ${oranges} = ${apples + oranges}`, '2 + 3 = 5');

function transformTemplate(strings, ...values) {
  assert.deepEqual(JSON.stringify(strings), '["Sum of "," + "," =\\n ","!"]');
  assert.deepEqual(JSON.stringify(strings.raw), '["Sum of "," + "," =\\\\n ","!"]');
  assert.deepEqual(JSON.stringify(values), '[3,5,8]');
}

apples = 3;
oranges = 5;
//          |  s[0] | v[0] |s[1]| v[1]  |s[2]  |      v[2]      |s[3]
transformTemplate`Sum of ${apples} + ${oranges} =\n ${apples + oranges}!`;

// Правильно
assert.equal(String.fromCodePoint(119987),  '𝒳');
// Неверно!
assert.equal(String.fromCharCode(119987), '풳' ); // will take into account only first 2 bytes

// https://learn.javascript.ru/es-object
let propName = "firstName";

let user = {
  [propName]: "V"
};

assert.equal(user.firstName, "V");

user = { name: "Вася" };
let visitor = { isAdmin: false, visits: true };
let admin = { isAdmin: true };
Object.assign(user, visitor, admin);
assert.equal(user.isAdmin, true);

let methodName = "getFirstName";

user = {
  // в квадратных скобках может быть любое выражение,
  // которое должно вернуть название метода
  [methodName]() {  // вместо [methodName]: function() {
    return "V";
  },
  sayHi() {
    return "Hi";
  }
};
assert.equal(user.getFirstName(), "V");
assert.equal(user.sayHi(), "Hi");

// https://learn.javascript.ru/es-class
class Animal {
  constructor(name) {
    this.name = name;
  }

  walk() {
    return "I walk: " + this.name;
  }
}

class Rabbit extends Animal {
  static get className() {
    return "Rabbit"
  }

  constructor() {
    // this.tt = "tt"; // can't be used before super invocation
    super("Rabbit"); // то же, что и Animal.call(this, "Кроль")
  }

  set setName(name) {
    this.name = name;
  }

  walk() {
    return super.walk() + " ...and jump!";
  }
}

let rabbit = new Rabbit();
// I walk: Вася
// and jump!
assert.equal(Rabbit.prototype.__proto__ == Animal.prototype, true);
assert.equal(rabbit.walk(), "I walk: Rabbit ...and jump!");
assert.equal(Rabbit.className, "Rabbit");
rabbit.setName = "R";
assert.equal(rabbit.name, "R");

// https://learn.javascript.ru/symbol
let sym = Symbol("name");
assert.equal(sym.toString(), "Symbol(name)");
assert.equal(Symbol("name") == Symbol("name"), false);
let name = Symbol.for("name");
// символ уже есть, чтение из реестра
assert.equal(Symbol.for("name") == name, true);

// https://learn.javascript.ru/iterator
// Custom iterator
let range = {
  from: 1,
  to: 1
};

// сделаем объект range итерируемым
range[Symbol.iterator] = function() {

  let current = this.from;
  let last = this.to;

  // метод должен вернуть объект с методом next()
  return {
    next() {
      if (current <= last) {
        return {
          done: false,
          value: current++
        };
      } else {
        return {
          done: true
        };
      }
    }

  }
};
for (let num of range) {
  assert.equal(num, 1);
}
assert.equal(Math.max(...range), 1);

let str = "Happy";
let iterator = str[Symbol.iterator]();

while (true) {
  let next = iterator.next();
  if (next.done)
    break;
  console.log(next.value);
}

// https://learn.javascript.ru/set-map
// Sets and Maps
let activeUsers = [
  {name: "V"},
  {name: "P"},
  {name: "M"}
];

let weakMap = new WeakMap();

// additional info user to Id
weakMap.set(activeUsers[0], 1)
  .set(activeUsers[1], 2)
  .set(activeUsers[2], 3);
// weakMap.set(activeUsers[1], 2);
// weakMap.set(activeUsers[2], 3);
// weakMap.set('Katya', 4); //can't add non null object

assert.equal(weakMap.get(activeUsers[0]), 1);
assert.equal(weakMap.get(activeUsers[1]), 2);
activeUsers.splice(0, 1); // remove V from weak map too
activeUsers.splice(0, 1); // remove P from weak map too

// https://learn.javascript.ru/promise
// Task 1. Make delay with promise
let delay = (delayInMillis) => {
  return new Promise((resolve, reject) => {
    setTimeout(resolve, delayInMillis);
  });
};

delay(10).then(() => {
  console.log("Hello from promise!");
  assert.equal("Hello!", "Hello!")
});

// Task 2. Make run consequently
let urls = [
  'user.json',
  'guest.json'
];

let result = [];

let delayedCopyArrayElement = (source, dest, index, delayInMillis) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      dest[index] = source[index];
      resolve(dest)
    }, delayInMillis);
  });
};

delayedCopyArrayElement(urls, result, result.length, 100)
.then(resultArray => delayedCopyArrayElement(urls, result, result.length, 100))
.then(resultArray => {
  assert.deepEqual(result, urls);
  assert.deepEqual(resultArray, urls);
  console.log(resultArray)
});

// Task 2. Solution from the website
let delayAndReturn = (result, delayInMillis) => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(result);
    }, delayInMillis);
  });
};

let chain = Promise.resolve();

let results = [];

// в цикле добавляем задачи в цепочку
urls.forEach(function(url) {
  chain = chain
    .then(() => delayAndReturn(url, 10))
    .then((result) => {
      results.push(result);
    });
});

// в конце — выводим результаты
chain.then(() => {
  assert.deepEqual(results, urls);
  console.log(results);
});

// https://learn.javascript.ru/generator
function* generateSequence() {
  yield 1;
  yield 2;
  return 3;
}

let generator = generateSequence();

let one = generator.next();

assert.equal(JSON.stringify(one), '{"value":1,"done":false}'); // {value: 1, done: false}

function* generateSequenceFromTo(start, end) {
  for (let i = start; i <= end; i++) yield i;
}

function* generateCompose() {

  // 0..9 composition of generators
  yield* generateSequenceFromTo(1, 3);

}

let a = [];
for (let i of generateCompose()) {
  a.push(i);
}
assert.deepEqual(a, [1, 2, 3]);

// passing to generator
function* gen() {
  // passing var into generator
  let result = yield 1;
  return result;
}

let g = gen();
let yieldOne = g.next().value;
setTimeout(() => {
  assert.equal(g.next(yieldOne + 1).value, 2);
}, 10);

// throwing error into generators
function* ge() {
  try {
    let result = yield "2 + 2?"; // (**)
  } catch(e) {
    assert.equal(e.message, "message");
  }
}
let gene = ge();
let question = gene.next().value;
gene.throw(new Error("message")); // (*)

// flattering async code

function sleep(millis) {
  return new Promise(resolve => setTimeout(resolve, 500));
}

function* delayAndReturnGenerator(result, millis, count) {
  let final = result;
  for(let i = 0; i < count; i++) {
    final = yield delayAndReturn(result, millis);
  }
  return result;
}

// генератор для получения и показа аватара
// он yield'ит промисы
function* flatteredCode() {
  let a = yield delayAndReturn("a", 10);
  let ab = yield delayAndReturn(delayAndReturn(a + "b", 10), 10);
  // generator delegation
  let abc = yield* delayAndReturnGenerator(ab + "c", 10, 3);
  let abcd = abc + "d";

  // just delay here
  yield sleep(500);

  return abcd;
}

// worker to process promises from generator
function execute(generator, yieldValue) {
  let next = generator.next(yieldValue);
  if (!next.done) {
    next.value.then(
      result => execute(generator, result),
      err => generator.throw(err)
    );
  } else {
    // process result from generator
    // commonly there is a callback or similar
    assert.equal(next.value, "abcd");
  }

}

execute( flatteredCode() );

// the same but with co

function delayAndThrow(error, millis) {
  return new Promise((resolve, reject) => {
    setTimeout(reject, millis, error);
  })
}

function* flatteredCodeCo() {
  let a = yield delayAndReturn("a", 10);
  let ab = yield delayAndReturn(delayAndReturn(a + "b", 10), 10);
  let abc = yield* delayAndReturnGenerator(ab + "c", 10, 3); // can be executed without *, but in this case stack won't be saved
  let abcd = abc + "d";

  let abcde;
  try {
    let e = yield delayAndThrow('e', 10);
    abcde = abcd + e;
  } catch (e) {
    abcde = abcd + e;
  }

  // just delay here
  yield sleep(500);

  return abcde;
}

co(flatteredCodeCo).then((result) => assert.equal(result, 'abcde'));


// My generator implementation
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

Generator.prototype.next = function () {
  if (this._nextIndex === this._funcArray.length)
    throw new Error("Already consumed!");
  let done = (this._funcArray.length - 1) === this._nextIndex;
  let value = this._funcArray[this._nextIndex](arguments);
  this._nextIndex++;
  return new GeneratorResult(value, done);
};

let myGen = new Generator([() => ":", (args) => args[0] + "3", (args) => console.log(args[0])]);
let firstNext = myGen.next();
assert.deepEqual(firstNext, {done: false, value: ":"} );
let secondNext = myGen.next(firstNext.value);
assert.deepEqual(secondNext, {done: false, value: ":3"} );
let thirdNext = myGen.next(secondNext.value);
assert.deepEqual(thirdNext, {done: true, value: undefined} );

// Todo implement co like function: like executor one? and another one which uses my generator