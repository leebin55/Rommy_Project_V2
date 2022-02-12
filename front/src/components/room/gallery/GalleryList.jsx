import React, { useEffect, useState } from 'react';
import axiosInstance from '../../../utils/AxiosInstance';
import GallerySingle from './GallerySingle';
import ImageList from '@mui/material/ImageList';
import ImgResister from './ImgRegisterModal';
import { useParams } from 'react-router-dom';

function GalleryList() {
  const [galleryList, setGalleryList] = useState([]);
  const { roomUser, roomId } = useParams();

  // 화면띄워질때 한번만 실행
  useEffect(() => {
    viewGalleryList();
  }, []);

  // server 에서 gallery 리스트를 가져옴
  // 리스트 가져올때 로그인한 유저가 눌렀는지 확인하는 데이터도 같이 가져오기
  // dto 만들기
  const viewGalleryList = async () => {
    await axiosInstance
      .get(`/rooms/${roomUser}/${roomId}/gallerys`)
      .then((res) => {
        console.log(res);
        setGalleryList(res.data);
      }); //end then
  }; // end viewGalleryList;

  return (
    <>
      <ImgResister />
      <ImageList sx={{ width: 650, height: 450 }}>
        {galleryList.length > 0 &&
          galleryList.map((gallery, index) => {
            return <GallerySingle gallery={gallery} index={index} />;
          })}
      </ImageList>
    </>
  );
}

export default GalleryList;
