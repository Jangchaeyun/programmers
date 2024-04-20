function solution(denum1, num1, denum2, num2) {
  var answer = [];
  let denum = denum1 * num2 + denum2 * num1;
  let num = num1 * num2;

  let a = denum;
  let b = num;

  const gcd = (a, b) => (a % b === 0 ? b : gcd(b, a % b));

  answer[0] = a / gcd(a, b);
  answer[1] = b / gcd(a, b);

  return answer;
}
