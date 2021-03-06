import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../../../utils/AxiosInstance';
import '../../../css/room/GalleryDetail.css';

function GalleryDetail() {
  const navigate = useNavigate();
  //http://localhost:3000/room/gallery/2 에 board_seq 값 가져오기
  const { userId, board_seq } = useParams();
  const [galleryInfo, setGalleryInfo] = useState({
    content: '',
    createDate: '',
    updateDate: '',
    likeCount: '',
    status: '',
    title: '',
    boardSeq: '',
    nickname: '',
  });

  useEffect(() => {
    viewGalleryInfo();
  }, []);

  const viewGalleryInfo = async () => {
    try {
      // userParam
      await axiosInstance
        .get(`/room/${userId}/gallery/detail?board_seq=${board_seq}`)
        .then((res) => {
          if (res.status === 200) {
            //console.log(res.data);
            setGalleryInfo(res.data);
          }
        });
    } catch (error) {
      alert('데이터를 불러올수 없음.');
      throw error;
    }
  };

  const updateClick = () => {
    navigate(
      `/room/${userId}/gallery?board_state=update&board_seq=${board_seq}`
    );
    // try {
    //   axios
    //     .get(
    //       `http://localhost:8080/room/gallery/update/${GalleryDetail.boarsSeq}`
    //     )
    //     .then((res) => {
    //       if (res.status === 200) {
    //         navigate(`/room/gallery/update/:${GalleryDetail.boarsSeq}`);
    //       }
    //     });
    // } catch (error) {}
  };
  const deleteClick = async () => {
    const result = window.confirm('삭제하시겠습니까?');
    if (result) {
      try {
        axiosInstance
          .delete(`/room/${userId}/gallery/${galleryInfo.boardSeq}`)
          .then((res) => {
            // alert(`${res.data} 번 글이 삭제되었습니다.`);
            navigate(`/room/${userId}/gallery`);
          });
      } catch (error) {
        alert(error.response.data);
        throw error;
      }
    } else {
      return;
    }
  };

  return (
    <div>
      <div>
        <div className="gallery-detail-header">
          <button
            onClick={() => {
              navigate(`/room/${userId}/gallery`);
            }}
          >
            뒤로
          </button>
          <p>{galleryInfo.boardSeq}</p>
          <h3>{galleryInfo.boardTitle}</h3>
          <button className="gallery-btn-modify" onClick={updateClick}>
            수정
          </button>
          <button onClick={deleteClick}>삭제</button>
        </div>
        <div
          className="gallery-post-content"
          dangerouslySetInnerHTML={{ __html: galleryInfo.boardContent }}
        />
        <p>{galleryInfo.boardCreateAt}</p>
        <p>{galleryInfo.boardLike}</p>
      </div>
    </div>
  );
}

export default GalleryDetail;
