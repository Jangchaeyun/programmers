function solution(a) {
  let front = a[0];
  let back = a[a.length - 1];
  let frontArr = [];
  let backArr = [];

  for (let i = 1; i < a.length - 1; i++) {
    let current = a[i];
    if (current < front) {
      front = current;
      frontArr.push(current);
    }
  }

  for (let i = 1; i < a.length - 1; i++) {
    let current = a[a.length - i - 1];
    if (current < back) {
      back = current;
      backArr.push(current);
    }
  }

  return [...new Set([...frontArr, ...backArr])].length + 2;
}
