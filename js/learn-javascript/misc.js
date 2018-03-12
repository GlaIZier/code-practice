var assert = require('assert');

// https://learn.javascript.ru/class-instanceof

function formatDate(date) { 

}

assert.equal(formatDate('2011-10-02'),  '02.10.11');
assert.equal(formatDate(1234567890), '14.02.09' );
assert.equal(formatDate([2014, 0, 1]), '01.01.14');
assert.equal(formatDate(new Date(2014, 0, 1)), '01.01.14');