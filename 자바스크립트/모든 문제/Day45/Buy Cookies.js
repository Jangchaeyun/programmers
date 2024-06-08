function solution(cookie) {
  let len = cookie.length;
  let answer = 0;

  for (let i = 0; i < len - 1; i++) {
    let left = i;
    let right = i + 1;
    let lsum = cookie[left];
    let rsum = cookie[right];

    while (true) {
      if (lsum === rsum && answer < lsum) {
        answer = lsum;
      } else if (lsum <= rsum && left !== 0) {
        lsum += cookie[--left];
      } else if (rsum <= lsum && right !== len - 1) {
        rsum += cookie[++right];
      } else {
        break;
      }
    }
  }

  return answer;
}
