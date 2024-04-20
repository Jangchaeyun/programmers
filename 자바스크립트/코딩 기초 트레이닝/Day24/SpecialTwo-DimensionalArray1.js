function solution(n) {
  const arr = Array(n).fill(Array(n).fill(0));

  return arr.map((v, i) => {
    return v.map((v2, i2) => (v2 = i == i2 ? 1 : 0));
  });
}
