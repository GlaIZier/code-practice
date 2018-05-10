const assert = require('assert');

// Promise example
const sleepAndGetData = (data, millis) => new Promise(
  (resolve, reject) => setTimeout(() => resolve(data), millis)
);

const sleepAndThrowError = (msg, millis) => new Promise(
  (resolve, reject) => setTimeout(() => reject(msg), millis)
);

const makeRequest = () =>
  sleepAndGetData("dddata", 10)
    .then(data => {
      assert.equal(data, "dddata");
      console.log(data);
      return "done"
    });

// Async-await example
const makeRequestAsync = async () => {
  const data = await sleepAndGetData("dddata", 20);
  assert.equal(data, "dddata");
  console.log(data);
  return "done"
};

makeRequest();
console.log(makeRequestAsync().then(done => console.log(done)));

// Errors processing
const makeRequestAndProcessError = () =>
  sleepAndThrowError("error", 10)
    .then(data => {
      console.log(data);
      return "done"
    })
    .catch(e => {
      assert.equal(e, "error");
      console.error(e)
    });

const makeRequestAsyncAndProcessError = async () => {
  try {
    const data = await sleepAndThrowError("error", 20);
    console.log(data);
    return "done"
  } catch (e) {
    assert.equal(e, "error");
    console.error(e);
  }
};

makeRequestAndProcessError();
makeRequestAsyncAndProcessError();

// inner calls
const makeRequestInnerCall = () =>
  sleepAndGetData("dddata", 10)
    .then(data => {
      sleepAndGetData(data + "2", 10)
        .then(data => {
          assert.equal(data, "dddata2");
          console.log(data);
          return "done"
        })
    });

// Async-await example
const makeRequestAsyncInnerCall = async () => {
  const data = await sleepAndGetData("dddata", 20);
  const data2 = await sleepAndGetData(data + "2", 20);
  assert.equal(data2, "dddata2");
  console.log(data2);
  return "done"
};

makeRequestInnerCall();
makeRequestAsyncInnerCall();
