function solution(n, results) {
  var answer = 0;
  const winGraph = [];
  const loseGraph = [];

  results.forEach((result) => {
    if (winGraph[result[0]]) {
      winGraph[result[0]].push(result[1]);
    } else {
      winGraph[result[0]] = [result[1]];
    }

    if (loseGraph[result[1]]) {
      loseGraph[result[1]].push(result[0]);
    } else {
      loseGraph[result[1]] = [result[0]];
    }
  });

  for (let i = 1; i <= n; i++) {
    if (bfs(winGraph, i) + bfs(loseGraph, i) === n - 1) {
      answer++;
    }
  }

  return answer;

  function bfs(graph, start) {
    const queue = [start];
    const visited = [];
    visited[start] = true;
    let result = 0;

    while (queue.length > 0) {
      const node = queue.shift();
      for (let nextNode of graph[node] || []) {
        if (visited[nextNode] !== true) {
          visited[nextNode] = true;
          queue.push(nextNode);
          result++;
        }
      }
    }

    return result;
  }
}
