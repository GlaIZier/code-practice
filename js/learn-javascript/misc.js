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