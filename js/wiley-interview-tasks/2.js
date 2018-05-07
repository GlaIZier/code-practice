// Task: Should print ‘true’ to console:
// console.log((2).plus(3) === 5);

Number.prototype.plus = function (n) {
  return this.valueOf() + n;
};

console.log(Number.prototype);
console.log((2).plus(3) === 5);