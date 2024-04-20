function solution(array, n) {
  let diff = [];
  array.sort((a, b) => a - b).map((v) => diff.push(Math.abs(v - n)));
  return array[diff.indexOf(Math.min.apply(null, diff))];
}
