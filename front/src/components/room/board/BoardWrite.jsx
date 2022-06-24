import { React, useState, useRef, useEffect } from 'react';
import Editor from './Editor';
import EditorToolbar from './EditorToolbar';
import { useParams, useNavigate } from 'react-router-dom';
import axiosInstance from '../../../utils/AxiosInstance';
import { writeValidate } from '../../../utils/validate';

function BoardWrite({ toBoardPage, upData }) {
  const navigate = useNavigate();
  const [boardStatus, setBoardStatus] = useState('PUBLIC');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [status, setStaust] = useState('');
  const boardTitle = useRef();

  const { roomUser, roomId } = useParams();

  const onChangeTitle = (e) => {
    setTitle(e.target.value);
  };

  const selectHandler = (e) => {
    setBoardStatus(e.target.value);
  };

  const writeSubmit = async () => {
    const result = writeValidate(title, content);
    if (upData && result) {
      await axiosInstance
        .patch(`/rooms/${roomUser}/${roomId}/boards`, {
          boardSeq: upData.boardSeq,
          title,
          content,
        })
        .then((res) => {
          console.log('update ', res);
          if (res.status === 200) {
            navigate(`/rooms/${roomUser}/${roomId}/boards`);
          } else {
            alert('fail');
          }
        });
      return;
    }
    if (result) {
      await axiosInstance
        .post(`/rooms/${roomUser}/${roomId}/boards`, {
          title,
          content,
          status: boardStatus,
        })
        .then((res) => {
          window.location.reload();
        });
    }
  };

  const updating = () => {
    setTitle(upData.title);
    setContent(upData.content);
    setBoardStatus(upData.boardPrivate);
  };

  useEffect(() => {
    if (upData) {
      console.log(upData);
      updating();
    }
  }, []);

  return (
    <div className="board-write-container">
      <div className="board-write-header">
        <select
          className="board-write-select"
          onChange={selectHandler}
          value={boardStatus}
        >
          <option value="PUBLIC">전체공개</option>
          <option value="FRIEND">친구공개</option>
          <option value="PRIVATE">비공개</option>
        </select>
        <input
          className="board-write-title"
          ref={boardTitle}
          placeholder="제목을 입력하세요"
          value={title}
          onChange={onChangeTitle}
        />
      </div>
      <div className="board-write-content">
        <EditorToolbar toolbarId={'qb'} />
        <Editor toolbarId={'qb'} content={content} setContent={setContent} />
      </div>
      <button type="button" onClick={() => writeSubmit()}>
        등록
      </button>
      <button type="button" onClick={() => toBoardPage('list')}>
        리스트로
      </button>
    </div>
  );
}

export default BoardWrite;
