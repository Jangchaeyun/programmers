function solution(land, P, Q) {
  const blocks = land.flat().sort((a, b) => a - b);
  const total = blocks.reduce((a, b) => a + b);

  let floor = -1;
  let prev = 0;
  let answer = Infinity;

  for (let i = 0; i < blocks.length; i++) {
    if (floor !== blocks[i]) {
      floor = blocks[i];

      const willBeAddedCount = blocks[i] * i - prev;
      const willBeDeletedCount = total - prev - (blocks.length - i) * blocks[i];
      const result = willBeAddedCount * P + willBeDeletedCount * Q;

      if (answer > result) answer = result;
    }

    prev += blocks[i];
  }

  return answer;
}
