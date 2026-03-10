import React from 'react';
import Sidebar from './Sidebar';

interface LayoutProps {
  children: React.ReactNode;
}

const Layout: React.FC<LayoutProps> = ({ children }) => {
  return (
    <div className="min-h-screen bg-beige-100 flex font-sans">
      <Sidebar />
      <main className="flex-1 ml-[300px] p-10 mt-[300-px]">
        <div className="max-w-[1200px] mx-auto w-full">
          {children}
        </div>
      </main>
    </div>
  );
};

export default Layout;
