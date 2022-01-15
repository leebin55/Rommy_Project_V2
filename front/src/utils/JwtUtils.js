import React from 'react';

const baseUrl = 'http://localhost:3000';
const JwtDecoder = (token) => {
  // [0]:  header , [1] : payload , [2] : Signature
  const base64Payload = token.split('.')[1];
  const payload = Buffer.from(base64Payload, 'base64');
  const result = JSON.parse(payload.toString());
  console.log(result);
  /**
   * 예시 {sub: '22', auth: 'ROLE_USER', exp: 1642232644}
   */
  return result;
};

const JwtValidate = () => {
  const token = localStorage.getItem('token');
  console.log('validate token 없음');
  if (token) {
    const { exp } = JwtDecoder(token);
    const now = (new Date().getTime() + 100) / 1000;
    console.log('now : ', now, ' exp ', exp);
    if (exp < now) {
      localStorage.removeItem('token');
      window.location.href = baseUrl + '/login';
      return false;
    }
    return true; // exp 이 now  보다 클때
  }
  window.location.href = baseUrl + '/login';
  return false; // token 없을때
};

export { JwtDecoder, JwtValidate };
