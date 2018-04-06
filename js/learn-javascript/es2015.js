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
