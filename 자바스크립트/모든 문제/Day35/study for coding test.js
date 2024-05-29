const solution = (alp, cop, problems) => {
  const MAX_INT = 2147483647;

  let alpMax = 0;
  let copMax = 0;
  for (let i = 0; i < problems.length; i++) {
    if (problems[i][0] > alpMax) alpMax = problems[i][0];
    if (problems[i][1] > copMax) copMax = problems[i][1];
  }
  if (alp > alpMax) alp = alpMax;
  if (cop > copMax) cop = copMax;

  const dp = Array.from(new Array(151), () => new Array(151).fill(MAX_INT));
  dp[alp][cop] = 0;

  for (let i = alp; i <= alpMax; i++) {
    for (let j = cop; j <= copMax; j++) {
      if (i == alpMax && j == copMax) break;

      if (i < alpMax) {
        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
      }

      if (j < copMax) {
        dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
      }

      for (let k = 0; k < problems.length; k++) {
        const [alpReq, copReq, alpRwd, copRwd, cost] = problems[k];

        if (i >= alpReq && j >= copReq) {
          const alpSum = i + alpRwd;
          const copSum = j + copRwd;

          if (alpSum >= alpMax && copSum >= copMax) {
            dp[alpMax][copMax] = Math.min(dp[alpMax][copMax], dp[i][j] + cost);
          } else if (alpSum >= alpMax) {
            dp[alpMax][j + copRwd] = Math.min(
              dp[alpMax][j + copRwd],
              dp[i][j] + cost
            );
          } else if (copSum >= copMax) {
            dp[i + alpRwd][copMax] = Math.min(
              dp[i + alpRwd][copMax],
              dp[i][j] + cost
            );
          } else {
            dp[i + alpRwd][j + copRwd] = Math.min(
              dp[i + alpRwd][j + copRwd],
              dp[i][j] + cost
            );
          }
        }
      }
    }
  }

  return dp[alpMax][copMax];
};
