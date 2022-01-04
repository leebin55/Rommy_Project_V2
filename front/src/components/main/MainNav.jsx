import React from 'react';
import { NavLink } from 'react-router-dom';

function MainNav() {
  return (
    <nav className="main-nav">
      <NavLink to="/">Home</NavLink>
      <NavLink to="/search">Search</NavLink>
      <NavLink to="/news">News</NavLink>
    </nav>
  );
}

export default MainNav;
