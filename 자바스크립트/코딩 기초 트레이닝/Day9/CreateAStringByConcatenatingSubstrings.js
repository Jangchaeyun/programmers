const solution = (my_strings, parts) => {
  let result = "";
  for (let i = 0; i < my_strings.length; i++) {
    let index = parts[i];
    let part = my_strings[i].substring(index[0], index[1] + 1);
    result += part;
  }
  return result;
};
