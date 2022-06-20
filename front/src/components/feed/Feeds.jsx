import React, { useState, useEffect } from 'react';
import Feed from './Feed';
import axiosInstance from '../../utils/AxiosInstance';
import '../../css/Feeds.css';

// 개별 피드
function Feeds() {
  const [galleryList, setGalleryList] = useState([]);

  useEffect(() => {
    viewGalleryList();
  }, []);

  // server 에서 gallery 리스트를 가져옴
  const viewGalleryList = async () => {
    try {
      await axiosInstance.get(`http://localhost:8080/feeds`).then((res) => {
        console.log(' feed 받은 데이터 : ', res.data);
        setGalleryList(res.data);
      }); //end then
    } catch (error) {
      console.log(error);
      // end try
      throw error;
    } //end catch
  }; // end viewGalleryList;

  return (
    <div>
      {galleryList.length > 0 ? (
        galleryList.map((gallery, index) => (
          <Feed gallery={gallery} index={index} />
        ))
      ) : (
        <p>아직 등록된 게시물이 없습니다.</p>
      )}
    </div>
  );
}

export default Feeds;
