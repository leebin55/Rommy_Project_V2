import { React, useState, useRef, useEffect } from 'react';
import Editor from './Editor';
import EditorToolbar from './EditorToolbar';
import { useNavigate, useParams } from 'react-router-dom';
import axiosInstance from '../../../utils/AxiosInstance';

function BoardWrite({ upData }) {
  const navigate = useNavigate();
  const [boardStatus, setBoardStatus] = useState('PUBLIC');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const boardTitle = useRef();

  const { roomUser, roomId } = useParams();

  const onChangeTitle = (e) => {
    setTitle(e.target.value);
  };

  const selectHandler = (e) => {
    setBoardStatus(e.target.value);
  };

  const writeSubmit = async () => {
    if (title.trim() === '') {
      alert('제목을 입력하세요');
      boardTitle.current.focus();
      return;
    } else if (content.trim() === '') {
      alert('내용을 입력하세요');
      return;
    }
    // update
    if (upData != null) {
      await axiosInstance
        .patch(`/rooms/${roomId}/board`, {
          boardSeq: upData.boardSeq,
          boardUserId: upData.boardUserId,
          boardTitle: title,
          boardContent: content,
          boardPrivate: boardStatus,
        })
        .then((res) => {
          if (res?.ok) {
            navigate(`/rooms/${roomUser}/${roomId}/boards`);
          }
        });
      //register
    } else {
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
    setTitle(upData.boardTitle);
    setContent(upData.boardContent);
    setBoardStatus(upData.boardPrivate);
  };

  useEffect(() => {
    if (upData) {
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
    </div>
  );
}

export default BoardWrite;
