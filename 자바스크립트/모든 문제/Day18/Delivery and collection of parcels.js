function solution(cap, n, deliveries, pickups) {
  var answer = -1;
  function lastFun(arr) {
    var len = arr.length;
    for (var i = len - 1; i >= 0; i--) {
      if (arr[i] !== 0) {
        return i;
      } else {
        arr.pop();
      }
    }
    return -1;
  }

  function delFun(arr, cap) {
    var index = lastFun(arr);
    var checkIndex = index;
    while (index !== -1 && cap > 0) {
      var index = lastFun(arr);
      if (cap <= arr[index]) {
        arr[index] -= cap;
        cap = 0;
        break;
      } else if (cap > arr[index]) {
        cap -= arr[index];
        arr[index] = 0;
        index--;
      }
    }

    return checkIndex;
  }

  function pickFun(arr, cap) {
    var index = lastFun(arr);
    var checkIndex = index;
    while (index !== -1 && cap > 0) {
      var index = lastFun(arr);
      if (cap <= arr[index]) {
        arr[index] -= cap;
        cap = 0;
        break;
      } else if (cap > arr[index]) {
        cap -= arr[index];
        arr[index] = 0;
        index--;
      }
    }

    return checkIndex;
  }

  while (true) {
    var delLen = delFun(deliveries, cap) + 1;
    var pickLen = pickFun(pickups, cap) + 1;
    if (delLen === 0 && pickLen === 0) {
      return answer + 1;
    } else if (delLen > pickLen) {
      answer += delLen * 2;
    } else {
      answer += pickLen * 2;
    }
  }
}
