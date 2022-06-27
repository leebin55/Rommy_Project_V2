const writeValidate = (title, content) => {
  if (title.trim() === '') {
    alert('제목을 입력하세요');
    return;
  }
  if (content.trim() === '') {
    alert('내용을 입력하세요');
    return;
  }
  return true;
};

const idValidate = (username) => {
  const regex = /^[a-z0-9]{2,10}$/;
  if (username === null || username.trim() === '') {
    alert(`ID 를 입력해 주세요`);
    return false;
  }
  if (!regex.test(username)) {
    alert('ID는  영문자, 소문자, 숫자 2~10자리 ');
    return false;
  }
  return true;
};

const pwValidate = (pw, rpw) => {
  const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
  if (pw === null || pw.trim() === '') {
    alert(`비밀번호 를 입력해 주세요`);
    return false;
  }
  if (!regex.test(pw)) {
    alert('비밀번호는 적어도 하나의 영문자와 숫자조합 8글자이상 20이하 ');
    return false;
  }
  if (pw !== rpw) {
    alert('password 가 일치하지 않습니다.');
    return false;
  }
  return true;
};

const emailValidate = (email) => {
  const regex = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
  if (email === null || email.trim() === '') {
    alert(`이메일을 입력해 주세요`);
    return false;
  }
  if (!regex.test(email)) {
    alert('올바른 이메일 형식이 아닙니다.');
    return false;
  }
  return true;
};

const nickValidate = (nickname) => {
  const regex = /^[0-9a-zA-Zㄱ-ㅎ가-힣]{1,10}$/;
  if (nickname === null || nickname.trim() === '') {
    alert(`닉네임을 입력해 주세요`);
    return false;
  }
  if (!regex.test(nickname)) {
    alert('닉네임은 한글,숫자, 영문자 조합 1 - 10 글자');
    return false;
  }
  return true;
};
export { writeValidate, idValidate, pwValidate, emailValidate, nickValidate };
