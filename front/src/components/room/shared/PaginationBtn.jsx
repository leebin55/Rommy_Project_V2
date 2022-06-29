import React from 'react';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';

export default function PaginationBtn({ pageData }) {
  const pageHandleChange = (event, value) => {
    pageData.setPage(value);
  };

  return (
    <Stack spacing={2} alignItems="center">
      <Pagination
        count={10}
        page={pageData.page}
        onChange={pageHandleChange}
        boundaryCount={5}
        color="primary"
      />
    </Stack>
  );
}
