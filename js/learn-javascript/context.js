var assert = require('assert');

// https://learn.javascript.ru/object-methods
var calculator = (function () {
  var _oper1, _oper2;

  return {
    read: function (oper1, oper2) {
      _oper1 = oper1;
      _oper2 = oper2;
    },
    sum: function () {
      return _oper1 + _oper2;
    }
  };

})();

calculator.read(1, 2);
assert.equal(calculator.sum(), 3);

var ladder = {
  step: 0,
  up: function () { // вверх по лестнице
    this.step++;
    return this;
  },
  down: function () { // вниз по лестнице
    this.step--;
    return this;
  },
  showStep: function () { // вывести текущую ступеньку
    return this.step;
  }
};

assert.equal(ladder.up().up().down().up().down().showStep(), 1);
assert.equal(!!({} && []), true);
assert.equal(isNaN(+{}), true);
assert.equal(+[], 0);
assert.equal({} && [], 0);
// as I understand {} casts to NaN, [] to 0, so the result of this is 0 => 0 == false
assert.equal(({} && []) == false, true);

var obj = {
  valueOf: function () {
    return 42;
  },
  toString: function () {
    return "42";
  }
};
assert.equal(obj + "", "42");
assert.equal(+obj, 42);
delete obj.valueOf;
assert.equal(+obj, "42");

// braces are the block of code here. It's equal to [0]
{
}
[0]
// braces are the block of code here. It's equal to + {} == NaN
{
}
+{}

var foo = {
  toString: function () {
    return 'foo';
  },
  valueOf: function () {
    return 2;
  }
};

assert.equal(foo.toString(), "foo");
// cast to the number
assert.equal(foo + 1, 3);
assert.equal(foo + "3", "23");

// Two objects are equal when they are the same
assert.equal([] == [], false);
assert.equal({} == {}, false);
// ![] = false => 0, [] => '' => 0
assert.equal([] == ![], true);

assert.equal(new Date(0) - 0, 0);
assert.equal(new Array(1)[0] + "", "undefined");
assert.equal(({})[0], undefined);
assert.equal([1] + 1, "11");
assert.equal([1, 2] + [3, 4], "1,23,4");
assert.equal([] + null + 1, "null1");
assert.equal([[0]][0][0], 0);
assert.equal(isNaN({} + {}), true);

// sum of parenthesis of any length
// my solution
var sum = function (n) {
  if (!sum.result)
    sum.result = 0;

  sum.valueOf = function () {
    var result = sum.result;
    // when it's called we need to clear result
    sum.result = undefined;
    return result;
  };

  sum.result += n;
  return sum;
};

assert.equal(sum(1) == 1, true);
assert.equal(sum(1)(2)(3) == 6, true);
assert.equal(sum(1)(2)(3)(-1) == 5, true);

// solution from the website
var sum1 = function (a) {

  var f = function (b) {
    a += b;
    return f;
  };

  f.valueOf = function () {
    return a;
  };

  return f;
};

// assert.equal(sum1(1) == 1, true);
assert.equal(sum1(1)(2)(3) == 6, true);
assert.equal(sum1(1)(2)(3)(-1) == 5, true);

// my solution without valueOf but with empty argument call in the end
var sum2 = function (n) {
  if (!sum2.result)
    sum2.result = 0;

  if (n) {
    sum2.result += n;
    return sum2;
  }
  else {
    var result = sum2.result;
    sum2.result = undefined;
    return result;
  }
};

assert.equal(sum2(1)(), 1);
assert.equal(sum2(1)(2)(3)(), 6);
assert.equal(sum2(1)(2)(3)(-1)(), 5);

// https://learn.javascript.ru/constructor-new
// return the same object by different constructors
var o = {};
function A() { return o; }
function B() { return o; }
assert.equal(new A, new B);

// calculator
Calculator = function() {

  this.methods = {
    '+': function(a, b) {
      return a + b;
    }
  };

  this.calculate = function(opActionOp) {
    opActionOp = opActionOp.trim();
    var operands = opActionOp.split(' ');
    // without this it will seek for methods in current function and then in global and won't find until we point
    // the actual object: calc.methods, but it is not flexible
    return this.methods[operands[1]](+operands[0], +operands[2]);
  };

  this.addMethod = function (operationString, operationFunc) {
    this.methods[operationString] = operationFunc;
  }

};
var calc = new Calculator();
assert.equal(calc.calculate('1 + 2'), 3);
calc.addMethod("*", function (a, b) {
  return a * b;
});
calc.addMethod("^", function (a, b) {
  return Math.pow(a, b);
});
assert.equal(calc.calculate('2 * 2'), 4);
assert.equal(calc.calculate('2 ^ 3'), 8);

// https://learn.javascript.ru/descriptors-getters-setters
function User(fullName) {
  this.fullName = fullName;

  Object.defineProperties(this, {
    firstName: {
      get: function () {
        return this.fullName.split(' ')[0]
      },
      set: function (firstName) {
        this.fullName = firstName + " " + this.lastName;
      }
    },

    lastName: {
      get: function () {
        return this.fullName.split(' ')[1]
      },
      set: function (lastName) {
        this.fullName = this.firstName + " " + lastName;
      }
    }
  });
}

var vasya = new User("Vasya Popkin");
// чтение firstName/lastName
assert.equal(vasya.firstName, "Vasya");
assert.equal(vasya.lastName, "Popkin");
vasya.firstName = "Vova";
vasya.lastName = "Bobkin";
assert.equal(vasya.firstName, "Vova");
assert.equal(vasya.lastName, "Bobkin");
assert.equal(vasya.fullName, "Vova Bobkin");

// https://learn.javascript.ru/static-properties-and-methods

function Article() {
  Article.count++;
}
Article.count = 0;

Article.showCount = function() {
  return this.count; // this in static method points to Article. The same as Article.count
};

// использование
new Article();
new Article();
assert.equal(Article.showCount(), 2);

// https://learn.javascript.ru/static-properties-and-methods

function Article2() {
  this.created = new Date();

  if (!Article2.count)
    Article2.count = 0;
  Article2.count++;
  Article2.lastDate = this.created;

  this.getStats = function() {
    return Article2.count + " " + Article2.lastDate;
  }
}

new Article2();
var a2 = new Article2();
assert.equal(a2.getStats().split(" ")[0], "2");


// https://learn.javascript.ru/call-apply

function sumArgs() {
  arguments.reduce = [].reduce;
  return arguments.reduce(function(a, b) {
    return a + b;
  });
}

assert.equal(sumArgs(1, 2, 3), 6);

function sumArgs1() {

  return [].reduce.call(arguments, function(a, b) {
    return a + b;
  });
}

assert.equal(sumArgs1(1, 2, 3), 6);

function applyAll(func) {
  // arguments doesn't have call()
  var args = [].slice.call(arguments, 1);

  return func.apply(null, args);
}

assert.equal(applyAll(Math.max, 1, 2, 3, 54), 54);
assert.equal(applyAll(Math.min, 1, 2, 3, -54), -54);

//https://learn.javascript.ru/bind

// task 2
function f() {
  return this;
}
var user = {
  g: f.bind("Hello")
};
assert.equal(user.g(), "Hello");

user = {
  g: f
};
assert.equal(user.g(), user);

// task 3
function f1() {
  return this.name;
}
f1 = f1.bind( {name: "1"} ).bind( {name: "2" } ); // second bind will connect context for the wrapper returned by the first bind
assert.equal(f1(), "1");

// task 4
function sayHi() {
  return this.name;
}
sayHi.test = 5;
var bound = sayHi.bind({
  name: "Вася"
});
assert.equal(bound.test, undefined);

// task 4. Using bind
function ask(input, answer, ok, fail) {
  if (input.toLowerCase() == answer.toLowerCase()) return ok();
  else return fail();
}

var user = {
  login: 'U',
  password: '12345',

  loginOk: function() {
    return this.login + " ok";
  },

  loginFail: function() {
    return this.login + " fail";
  },

  checkPassword: function(input) {
    return ask(input, this.password, this.loginOk.bind(this), this.loginFail.bind(this));
  }
};

assert.equal(user.checkPassword('12345'), "U ok");

// Task 4. Using closure
var user1 = {
  login: 'U',
  password: '12345',

  loginOk: function() {
    return this.login + " ok";
  },

  loginFail: function() {
    return this.login + " fail";
  },

  checkPassword: function(input) {
    return ask(input, this.password,
      (function (self) {
        return function() {
          return self.loginOk();
        };
      }(this)),
      (function (self) {
        return function() {
          return self.loginFail();
        };
      }(this))
    )
  }
};

assert.equal(user1.checkPassword('12345'), "U ok");


// Task 4. Using closure from the website

var user2 = {
  login: 'U',
  password: '12345',

  loginOk: function() {
    return this.login + " ok";
  },

  loginFail: function() {
    return this.login + " fail";
  },

  checkPassword: function(input) {
    return ask(input, this.password,
      function () {
        return user2.loginOk();
      },
      function () {
          return user2.loginFail();
      }
    )
  }
};

assert.equal(user2.checkPassword('12345'), "U ok");

// Task 5 bind
var user3 = {
  login: 'U',
  password: '12345',

  loginDone: function(result) {
    return this.login + (result ? ' ok' : ' fail');
  },

  checkPassword: function(input) {
    return ask(input, this.password, this.loginDone.bind(this, true), this.loginDone.bind(this, false));

  }
};

var vasya1 = user3;
user3 = null;
assert.equal(vasya1.checkPassword("12345"), "U ok");

// Task 5 closure

var user4 = {
  login: 'U',
  password: '12345',

  loginDone: function(result) {
    return this.login + (result ? ' ok' : ' fail');
  },

  checkPassword: function(input) {
    return ask(input, this.password,
      (function (self, result) {
        return function() {
          return self.loginDone(result);
        };
      }(this, true)),
      (function (self, result) {
        return function() {
          return self.loginDone(result);
        };
      }(this, false))
    )
  }
};


// Task 5 variable from closure

var user5 = {
  login: 'U',
  password: '12345',

  loginDone: function(result) {
    return this.login + (result ? ' ok' : ' fail');
  },

  checkPassword: function(input) {
    var self = this;
    return ask(input, this.password,
      function () {
        return self.loginDone(true);
      },
      function () {
        return self.loginFail(true);
      }
    )
  }
};

vasya1 = user5;
user5 = null;
assert.equal(vasya1.checkPassword("12345"), "U ok");

// https://learn.javascript.ru/decorators
// Task 1

function square(a) {
  return a * a;
}

function makeLogging(f, log) {
  return function(arg) {
    log.push(arg);
    return f.apply(this, arguments);
  }
}

var log = [];
square = makeLogging(square, log);

assert.equal(square(1), 1);
assert.equal(square(5), 25);

assert.equal(log[0], 1);
assert.equal(log[1], 5);


// Task 3

function random(x) {
  return Math.random() * x;
}

function makeCaching(f) {
  var cache = {};

  return function(x) {
    if (!(x in cache)) {
      cache[x] = f.call(this, x);
    }
    return cache[x];
  };
}

random = makeCaching(random);

var a, b;

a = random(1);
b = random(1);
assert.equal(a, b);

b = random(2);
assert.notEqual(a, b);
