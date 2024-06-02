function solution(board) {
  const N = board.length;
  const dirs = [
    [0, 1],
    [1, 0],
    [0, -1],
    [-1, 0],
  ];

  const q = [
    [0, 0, 0, 0],
    [0, 0, 1, 0],
  ];

  const dp = Array.from({ length: N }, () =>
    Array.from({ length: N }, () => Array(dirs.length).fill(Infinity))
  );

  const isInBoard = (x, y) =>
    x >= 0 && x < N && y >= 0 && y < N && board[x][y] !== 1;

  while (q.length) {
    const [x, y, pDril, cost] = q.shift();

    dirs.forEach(([dx, dy], nDril) => {
      const [nx, ny] = [x + dx, y + dy];
      if (!isInBoard(nx, ny)) return;

      const newCost = cost + (pDril === nDril ? 100 : 600);

      if (newCost < dp[nx][ny][nDril]) {
        dp[nx][ny][nDril] = newCost;
        q.push([nx, ny, nDril, newCost]);
      }
    });
  }

  return Math.min(...dp[N - 1][N - 1]);
}
