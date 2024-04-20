function solution(arr, queries) {
  var answer = [...arr];
  for (let i = 0; i < queries.length; i++) {
    let box = answer[queries[i][0]];
    answer[queries[i][0]] = answer[queries[i][1]];
    answer[queries[i][1]] = box;
  }

  return answer;
}
