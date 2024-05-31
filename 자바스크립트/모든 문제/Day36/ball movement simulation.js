function solution(n, m, x, y, queries) {
  const dx = [0, 0, 1, -1];
  const dy = [1, -1, 0, 0];
  function calNextRange(s, e, move, max) {
    const nextS = s === 0 && move > 0 ? 0 : s + move;
    const nextE = e === max - 1 && move < 0 ? max - 1 : e + move;

    if ((nextS < 0 || nextS >= max) && (nextE < 0 || nextE >= max)) {
      return [-1, -1];
    }
    if (nextS < 0 && nextE >= 0 && nextE < max) {
      return [0, nextE];
    }
    if (nextE >= max && nextS >= 0 && nextS < max) {
      return [nextS, max - 1];
    }
    return [nextS, nextE];
  }

  let [sx, ex, sy, ey] = [x, x, y, y];

  for (let i = queries.length - 1; i >= 0; i--) {
    const [dir, cnt] = queries[i];

    if (dir === 0 || dir === 1) {
      const res = calNextRange(sy, ey, cnt * dy[dir], m);
      if (res[0] === -1) return 0;
      [sy, ey] = res;
    } else {
      const res = calNextRange(sx, ex, cnt * dx[dir], n);
      if (res[0] === -1) return 0;
      [sx, ex] = res;
    }
  }
  return BigInt(ex - sx + 1) * BigInt(ey - sy + 1);
}
