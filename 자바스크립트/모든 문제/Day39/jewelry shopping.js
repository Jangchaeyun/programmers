function solution(gems) {
  const cnt = new Set(gems).size;
  var ans = [1, gems.length];

  var l = 0,
    r = 0;
  const hit = new Map();
  hit.set(gems[0], 1);

  while (r < gems.length) {
    if (hit.size === cnt) {
      if (ans[1] - ans[0] > r - l) ans = [l + 1, r + 1];

      hit.set(gems[l], hit.get(gems[l]) - 1);
      if (hit.get(gems[l]) === 0) hit.delete(gems[l]);
      l++;
    } else {
      r++;
      const right = hit.get(gems[r]);
      hit.set(gems[r], right ? right + 1 : 1);
    }
  }

  return ans;
}
