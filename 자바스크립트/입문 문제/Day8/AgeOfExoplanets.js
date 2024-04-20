function solution(age) {
    let str = 'abcdefghij';
    return Array.from(age.toString()).map(t => str[+t]).join('');
}