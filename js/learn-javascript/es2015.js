let assert = require('assert');
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