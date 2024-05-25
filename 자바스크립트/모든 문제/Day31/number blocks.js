function solution(begin, end) {
  var answer = [];

  function check(num) {
    var checkArr = [];
    if (num === 1) {
      return 0;
    }
    for (var i = 2; i <= Math.sqrt(num); i++) {
      if (num % i === 0) {
        checkArr.push(i);
        if (num / i <= 1e7) {
          return num / i;
        }
      }
    }
    if (checkArr.length !== 0) {
      return checkArr[checkArr.length - 1];
    }
    return 1;
  }

  for (var i = begin; i <= end; i++) {
    var checkNum = check(i);
    if (checkNum !== undefined) {
      answer.push(checkNum);
    }
  }
  return answer;
}
