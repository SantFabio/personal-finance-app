

const Sidebar = () => {
  const navItems = [
    { name: 'Overview', icon: 'M3 13h8V3H3v10zm0 8h8v-6H3v6zm10 0h8V11h-8v10zm0-18v6h8V3h-8z', active: true },
    { name: 'Transactions', icon: 'M16 1h-3.218c-.417-1.166-1.514-2-2.782-2s-2.365.834-2.782 2H4c-1.1 0-2 .9-2 2v18c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V3c0-1.1-.9-2-2-2zm-6 0c.55 0 1 .45 1 1s-.45 1-1 1-1-.45-1-1 .45-1 1-1zm6 20H4V3h2v3h8V3h2v18z' },
    { name: 'Budgets', icon: 'M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-6h2v6zm0-8h-2V7h2v2z' },
    { name: 'Pots', icon: 'M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-1 17.93c-3.95-.49-7-3.85-7-7.93 0-.62.08-1.21.21-1.79L9 15v1c0 1.1.9 2 2 2v1.93zm6.9-2.54c-.26-.81-1-1.39-1.9-1.39h-1v-3c0-.55-.45-1-1-1H8v-2h2c.55 0 1-.45 1-1V7h2c1.1 0 2-.9 2-2v-.41c2.93 1.19 5 4.06 5 7.41 0 2.08-.8 3.97-2.1 5.39z' },
    { name: 'Recurring Bills', icon: 'M19 3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16H5V5h14v14zM7 10h2v7H7zm4-3h2v10h-2zm4 6h2v4h-2z' }
  ];

  return (
    <aside className="w-[300px] h-screen bg-grey-900 rounded-tr-3xl flex flex-col pt-10 pb-6 text-white fixed left-0 top-0">
      <div className="px-8 mb-16">
        <h1 className="text-3xl font-bold tracking-tight">finance</h1>
      </div>

      <nav className="flex-1 space-y-2">
        {navItems.map((item) => (
          <a
            key={item.name}
            href="#"
            className={`flex items-center px-8 py-4 space-x-4 relative transition-colors ${item.active
              ? 'bg-beige-100 text-grey-900 font-bold'
              : 'text-grey-300 hover:text-white'
              }`}
          >
            {item.active && (
              <div className="absolute left-0 top-0 bottom-0 w-1 bg-secondary-green rounded-r-md"></div>
            )}
            <svg
              viewBox="0 0 24 24"
              fill="currentColor"
              className={`w-6 h-6 ${item.active ? 'text-secondary-green' : 'text-grey-500'}`}
            >
              <path d={item.icon} />
            </svg>
            <span className="text-lg">{item.name}</span>
          </a>
        ))}
      </nav>

      <div className="px-8 mt-auto">
        <button className="flex items-center space-x-4 text-grey-500 hover:text-white transition-colors">
          <svg viewBox="0 0 24 24" fill="currentColor" className="w-6 h-6">
            <path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" />
          </svg>
          <span className="text-lg font-bold">Minimize Menu</span>
        </button>
      </div>
    </aside>
  );
};

export default Sidebar;
