export const writeValidate = (title, content) => {
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
