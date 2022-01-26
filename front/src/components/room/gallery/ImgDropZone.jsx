import { useCallback, useState } from 'react';
import { useDropzone } from 'react-dropzone';
import { Paper } from '@mui/material';
import Box from '@mui/material/Box';

function ImgDropZone() {
  const [selectedImgFile, setSelectedImgFile] = useState('');

  const onDrop = useCallback((acceptedFiles) => {
    console.log(acceptedFiles);
  }, []);

  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });
  const { ref, ...rootProps } = getRootProps();

  const boxStyle = {
    display: 'flex',
    flexWrap: 'wrap',
    '& > :not(style)': {
      m: 1,
      width: 128,
      height: 128,
    },
  };
  return (
    <>
      <input {...getInputProps()} />
      <p>Drag 'n drop som files hear, or click select files</p>
    </>
  );
}

export default ImgDropZone;
