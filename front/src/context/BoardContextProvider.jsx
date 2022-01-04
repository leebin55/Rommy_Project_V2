import React, { createContext, useContext, useState } from 'react';

const AppContext = createContext();
export const useBoardContext = () => useContext(AppContext);

function BoardContextProvider({ childern }) {
  const [content, setContent] = useState('');
  const [title, setTitle] = useState('');
  const [galleryImg, setGalleryImg] = useState('');

  const [userId, setUserId] = useState('');

  const [isUpdate, setIsUpdate] = useState(false);
  const [isDetail, setIsDetail] = useState(false);
  const [galleryImgList, setGalleryImgList] = useState([]);
  const [galleryInfo, setGalleryInfo] = useState({
    boardCode: '',
    boardContent: '',
    boardCreateAt: '',
    boardLike: '',
    boardPrivate: '',
    boardSeq: '',
    boardTitle: '',
    boardUpdateAt: '',
    boardUserSeq: '',
  });

  const propsData = {
    content,
    setContent,
    title,
    setTitle,
    galleryImg,
    setGalleryImg,
    isUpdate,
    setIsUpdate,
    galleryInfo,
    setGalleryInfo,
    isDetail,
    setIsDetail,
    galleryImgList,
    setGalleryImgList,
  };

  const [updating, setUpdating] = useState();
  const Data = {
    updating,
  };
  return <AppContext.Provider value={Data}>{childern}</AppContext.Provider>;
}

export default BoardContextProvider;
