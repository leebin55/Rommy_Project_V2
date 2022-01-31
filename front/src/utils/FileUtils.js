function returnFileURL(file) {
  const baseURL = 'http://localhost:8080/uploads';
  return `${baseURL}/${file}`;
}

export default returnFileURL;
