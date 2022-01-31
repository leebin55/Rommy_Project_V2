export default function MainRecentBoard({ list, navigate }) {
  const clickList = (e) => {
    console.log(window.location.pathname);
    navigate(`${window.location.pathname}/boards/${e.target.value}`);
  };
  return (
    <div className="main-recent-board-box">
      <ul>
        {list.length > 0 ? (
          list.map((item) => (
            <li onClick={clickList} value={item.boardSeq}>
              {item.title}
            </li>
          ))
        ) : (
          <li>게시물이 없습니다.</li>
        )}
      </ul>
    </div>
  );
}
