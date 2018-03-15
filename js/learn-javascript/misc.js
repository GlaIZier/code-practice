var assert = require('assert');

// https://learn.javascript.ru/class-instanceof

function formatDate(date) {
  var resultDate;
  var userTimezoneOffset; // have issues with timezone while creating new date
  if (typeof(date) === 'string') {
    resultDate = new Date(date);
    resultDate.setUTCHours(0, 0, 0, 0);
    return resultDate;
  } else if (typeof(date) === 'number') {
    // number of seconds since
    resultDate = new Date(date * 1000);
    resultDate.setUTCHours(0, 0, 0, 0);
    return resultDate;
  } else if (Array.isArray(date)) {
    var newDate = new Date(date[0], date[1], date[2]);
    userTimezoneOffset = newDate.getTimezoneOffset() * 60000;
    return new Date(newDate.getTime() - userTimezoneOffset);
  } else if ({}.toString.call(date).slice(8, -1) == 'Date') { // [object Date] => Date
    userTimezoneOffset = date.getTimezoneOffset() * 60000;
    return new Date(date.getTime() - userTimezoneOffset);
  } else {
    return 'unknown';
  }
}

assert.deepEqual(formatDate('2011-10-02'),  new Date('2011-10-02'));
assert.deepEqual(formatDate(1234567890), new Date('2009-02-13') );
assert.deepEqual(formatDate(60 * 60 * 24 + 1), new Date('1970-01-02') );
assert.deepEqual(formatDate(1), new Date('1970-01-01') );
assert.deepEqual(formatDate([2014, 0, 1]), new Date('2014-01-01'));
assert.deepEqual(formatDate(new Date(2014, 0, 1)), new Date('2014-01-01'));

// https://learn.javascript.ru/json

var leader = {
  id: 1,
  name: "Василий Иванович"
};
var soldier = {
  id: 2,
  name: "Петька"
};
leader.soldier = soldier.id;
soldier.leader = leader.id;
var team = [leader, soldier];
assert.equal(JSON.stringify(team), '[{"id":1,"name":"Василий Иванович","soldier":2},{"id":2,"name":"Петька","leader":1}]');

// https://learn.javascript.ru/settimeout-setinterval
// Task 1
var count = 1;
var previousCount = 0;
var timer = setInterval(function() {
  if (count === 3)
    clearInterval(timer);
  count++;
  previousCount++;
  assert.equal(previousCount + 1, count);
}, 10);

// Task 2
var count1 = 1;
var previousCount1 = 0;
var timer1 = setTimeout(function recursiveTimeout() {
  if (count1 === 3)
    return;
  count1++;
  previousCount1++;
  assert.equal(previousCount1 + 1, count1);
  setTimeout(recursiveTimeout, 10);
}, 10);

// Task 7 delay
function f(x) {
  console.log(x);
  return x;
}
function delay(f, millis) {
  return function (x) {
    setTimeout(f, millis, x);
  };
}
function webSiteDelay(f, ms) {
  return function() {
    var savedThis = this;
    var savedArgs = arguments;
    setTimeout(function() {
      f.apply(savedThis, savedArgs);
    }, ms);
  };

}

var f50 = delay(f, 50);
f50("test");

// Task 8 Debounce
function debounce(f, delayInMillis) {
  var timerId;
  return function() {
    clearTimeout(timerId);
    var self = this;
    var args = arguments;
    timerId = setTimeout(function () {
      f.apply(self, args);
      timerId = null;
    }, delayInMillis);
  };
}

var f = debounce(f, 1000); // it's ok to name wrapper the same name

f(1); // вызов отложен на 1000 мс
f(2); // предыдущий отложенный вызов игнорируется, текущий (2) откладывается на 1000 мс

// через 1 секунду будет выполнен вызов f(1)

setTimeout( function() { f(3) }, 1100); // через 1100 мс отложим вызов еще на 1000 мс
setTimeout( function() { f(4) }, 1200); // игнорируем вызов (3)

// через 2200 мс от начала выполнения будет выполнен вызов f(4)