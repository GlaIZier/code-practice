// Task: Should log ‘true’ to console. Implement for arbitrary functions call:
// console.log(sum(2)(3) == 5);

// First, simple solution
function simpleSum(a) {
  return function (b) {
    return a + b;
  }
}
console.log(simpleSum(2)(3) == 5);

// Second, more smart solution
function simpleCompose(f) {
  return function (a) {
    return function (b) {
      return f(a, b);
    }
  }
}

function sum() {
  return [].reduce.call(arguments, function(a, b) {
    return a + b;
  });
}

console.log(simpleCompose(sum)(2)(3) == 5);


// Third, the smartest solution which use any function and any number of arguments

function compose(f, defaultValue) {

  let result = defaultValue;

  let closedCompose = function (f) {
    let recurCompose = function (curValue) {
      result = f(result, curValue);
      return recurCompose;
    };
    recurCompose.valueOf = function () {
      return result;
    };
    return recurCompose;
  };

  return closedCompose(f);
}

console.log(compose(sum, 0)(2)(3)(4) == 9);
console.log(compose(sum, 0)(2)(3) == 5);
