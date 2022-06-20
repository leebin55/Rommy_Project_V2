import React, { createContext, useContext, useState } from 'react';

//context 생성
const GalleryContext = createContext();

//Context 의 Store 에 보관된 정보들을 추출하기 위한 Hook함수 선언
export const useGalleryContext = () => useContext(GalleryContext);

//Provider 를 합성 패턴으로 선언하여 필요한 곳에서 끌어올려 사용할 수 있도록 함
const GalleryContextProvider = ({ children }) => {};

export default GalleryContextProvider;
