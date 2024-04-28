function solution(k, m, score) {
  if (score.length < m) {
    return 0;
  }

  score.sort((a, b) => a - b);

  let total = 0;

  while (score.length >= m) {
    const box = score.splice(score.length - m, m);

    const price = m * box[0];

    total += price;
  }

  return total;
}
