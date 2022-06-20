import React, { useMemo, useRef } from 'react';
import ReactQuill, { Quill } from 'react-quill';
import axiosInstance from '../../../utils/AxiosInstance';
import 'react-quill/dist/quill.snow.css';
import returnFileURL from '../../../utils/FileUtils';

function undoChange() {
  this.quill.history.undo();
}
function redoChange() {
  this.quill.history.redo();
}

const Size = Quill.import('formats/size');
Size.whitelist = ['small', 'medium', 'large'];
Quill.register(Size, true);

const Font = Quill.import('formats/font');
Font.whitelist = ['arial', 'comic-sans', 'courier-new', 'georgia'];
Quill.register(Font, true);

const formats = [
  'font',
  'size',
  'bold',
  'italic',
  'underline',
  'align',
  'background',
  'color',
  'image',
];

function Editor({ toolbarId, content, setContent }) {
  const quillRef = useRef();

  const imageHandler = () => {
    const img_input = document.createElement('input');
    img_input.setAttribute('type', 'file');
    img_input.setAttribute('accept', 'image/*');
    img_input.click();
    img_input.addEventListener('change', async () => {
      const file = img_input.files[0];

      if (file.size > 2097152) {
        alert('파일용량을 초과하였습니다.');
        return;
      }

      const formData = new FormData();
      formData.append('img', file);
      console.log('form data : ', formData);
      try {
        await axiosInstance.post(`/files`, formData).then((result) => {
          console.log(result.data);

          const img_url = returnFileURL(result.data);

          const editor = quillRef.current.getEditor(); // 에디터 정보 가져오기
          const range = editor.getSelection(); // 현재 커서 위치
          editor.insertEmbed(range, 'image', img_url);
        });
      } catch (error) {
        alert('다시 시도해 주세요');
        throw error;
      }
    });
  };

  const modules = useMemo(
    () => ({
      toolbar: {
        container: '#' + toolbarId,
        handlers: {
          undo: undoChange,
          redo: redoChange,
          image: imageHandler,
        },
      },
      history: {
        delay: 500,
        maxStack: 100,
        userOnly: true,
      },
    }),
    []
  );

  return (
    <ReactQuill
      ref={quillRef}
      theme="snow"
      value={content}
      onChange={(value) => {
        setContent(value);
      }}
      modules={modules}
      formats={formats}
      style={{ height: '350px' }}
    />
  );
}

export default Editor;
