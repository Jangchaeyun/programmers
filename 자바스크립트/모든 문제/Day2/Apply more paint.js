function solution(n, m, sections) {
  var answer = 0;
  var pointed = 0;
  for (var section of sections) {
    if (pointed < section) {
      answer++;
      pointed = section + m - 1;
    }
  }

  return answer;
}
