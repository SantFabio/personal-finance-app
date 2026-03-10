

const PotsSummary = () => {
  return (
    <div className="bg-white rounded-xl p-8 shadow-sm">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl font-bold text-grey-900">Pots</h2>
        <button className="text-grey-500 hover:text-grey-900 text-sm flex items-center">
          See Details <span className="ml-2">›</span>
        </button>
      </div>

      <div className="flex flex-col md:flex-row gap-6">
        {/* Total Saved Card */}
        <div className="bg-beige-100 rounded-xl p-6 flex items-center gap-4 flex-1">
          <div className="w-10 h-10 rounded-full bg-secondary-green flex items-center justify-center text-white">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
              <path d="M12 2v20M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6" />
            </svg>
          </div>
          <div>
            <p className="text-sm text-grey-500 mb-1">Total Saved</p>
            <p className="text-3xl font-bold text-grey-900">$850</p>
          </div>
        </div>

        {/* Categories Grid */}
        <div className="grid grid-cols-2 gap-4 flex-1">
          <div className="flex items-center gap-3">
            <div className="w-1 h-full min-h-[40px] bg-secondary-green rounded-full"></div>
            <div>
              <p className="text-xs text-gray-500 mb-1">Savings</p>
              <p className="font-bold text-grey-900">$159</p>
            </div>
          </div>

          <div className="flex items-center gap-3">
            <div className="w-1 h-full min-h-[40px] bg-secondary-cyan rounded-full"></div>
            <div>
              <p className="text-xs text-gray-500 mb-1">Gift</p>
              <p className="font-bold text-grey-900">$40</p>
            </div>
          </div>

          <div className="flex items-center gap-3">
            <div className="w-1 h-full min-h-[40px] bg-secondary-navy rounded-full"></div>
            <div>
              <p className="text-xs text-gray-500 mb-1">Concert Ticket</p>
              <p className="font-bold text-grey-900">$110</p>
            </div>
          </div>

          <div className="flex items-center gap-3">
            <div className="w-1 h-full min-h-[40px] bg-secondary-yellow rounded-full"></div>
            <div>
              <p className="text-xs text-grey-500 mb-1">New Laptop</p>
              <p className="font-bold text-grey-900">$10</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PotsSummary;
