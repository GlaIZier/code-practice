// Task: resolve issues with the same id;
function makeDroids() {

  var droids = [];

  /* test */
  for (var i = 0; i < 10; i++) {
    var droid = function() {
      console.log("R2D" + i);
    };
    droids.push(droid);
  }
  /* test */

  return droids;
}

// using let
function makeDroids1() {

  var droids = [];

  /* test */
  for (let i = 0; i < 10; i++) {
    var droid = function() {
      console.log("R2D" + i);
    };
    droids.push(droid);
  }
  /* test */

  return droids;
}

// external closure
function makeDroids2() {

  var droids = [];

  /* test */
  for (var i = 0; i < 10; i++) {
    (function (ii) {
      var droid = function() {
        console.log("R2D" + ii);
      };
      droids.push(droid);
    })(i);
  }
  /* test */

  return droids;
}

// Another closure
function makeDroids3() {

  var droids = [];

  /* test */
  for (var i = 0; i < 10; i++) {
    var droid = function(ii) {
      return function () {
        console.log("R2D" + ii);
      }
    };
    droids.push(droid(i));
  }
  /* test */

  return droids;
}

// static function variable
function makeDroids4() {

  var droids = [];

  /* test */
  for (var i = 0; i < 10; i++) {
    function droid() {
      console.log("R2D" + droid.i);
    }
    droid.i = i;
    droids.push(droid);
  }
  /* test */

  return droids;
}

for (let d of makeDroids4()) {
  d();
}
