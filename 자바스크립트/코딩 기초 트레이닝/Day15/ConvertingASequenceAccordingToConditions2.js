function solution(arr) {
  let cnt = 0;
  let pArr = arr;
  while (true) {
    const cArr = pArr.map((x) =>
      x >= 50 && x % 2 === 0 ? (x /= 2) : x < 50 && x % 2 === 1 ? x * 2 + 1 : x
    );
    if (pArr.every((x, i) => x === cArr[i])) break;
    cnt++;
    pArr = cArr;
  }
  return cnt;
}
