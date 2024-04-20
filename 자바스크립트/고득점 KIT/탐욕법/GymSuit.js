function solution(n, lost, reverse) {
  var answer = n - lost.length;

  let realLost = lost.filter((l) => !reverse.includes(l));
  let realReserve = reverse.filter((r) => !lost.includes(r));
  answer += lost.length - realLost.length;

  realLost.sort((a, b) => a - b);

  realLost.forEach((l) => {
    if (realReserve.length === 0) {
      return;
    }

    if (realReserve.includes(l - 1)) {
      realReserve = realReserve.filter((r) => r !== l - 1);
      answer++;
    } else if (realReserve.includes(l + 1)) {
      realReserve = realReserve.filter((r) => r != l + 1);
      answer++;
    }
  });
  return answer;
}
