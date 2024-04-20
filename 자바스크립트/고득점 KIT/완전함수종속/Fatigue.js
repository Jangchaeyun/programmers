function solution(k, duugeons) {
  let answer = 0;
  const visited = Array.from({ length: duugeons.length }, () => false);

  function DFS(hp, L) {
    for (let i = 0; i < duugeons.length; i++) {
      if (!visited[i] && duugeons[i][0] <= hp) {
        visited[i] = true;
        DFS(hp - duugeons[i][1], L + 1);
        visited[i] = false;
      }
    }

    answer = Math.max(answer, L);
  }

  DFS(k, 0);

  return answer;
}
