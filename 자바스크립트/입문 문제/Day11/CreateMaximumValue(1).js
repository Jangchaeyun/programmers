function solution(numbers) {
  numbers.sort((a, b) => b - a);
  const twoMaxima = numbers.splice(0, 2);
  return twoMaxima[0] * twoMaxima[1];
}
