

const BudgetsSummary = () => {
  return (
    <div className="bg-white rounded-xl p-8 shadow-sm h-full">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl font-bold text-grey-900">Budgets</h2>
        <button className="text-grey-500 hover:text-grey-900 text-sm flex items-center">
          See Details <span className="ml-2">›</span>
        </button>
      </div>

      <div className="flex gap-8 items-center h-[240px]">
        {/* Simplified Donut Chart Representation using CSS */}
        <div className="relative w-48 h-48 flex-shrink-0">
          {/* Base circle background */}
          <div className="absolute inset-0 rounded-full border-[16px] border-beige-100 object-cover"></div>

          {/* Custom borders for different category parts (visual approximation for this plan) */}
          <svg className="w-full h-full transform -rotate-90">
            <circle cx="96" cy="96" r="80" fill="transparent" stroke="#277C78" strokeWidth="32" strokeDasharray="502" strokeDashoffset="350"></circle>
            <circle cx="96" cy="96" r="80" fill="transparent" stroke="#82C9D7" strokeWidth="32" strokeDasharray="502" strokeDashoffset="420"></circle>
            <circle cx="96" cy="96" r="80" fill="transparent" stroke="#626070" strokeWidth="32" strokeDasharray="502" strokeDashoffset="480"></circle>
            <circle cx="96" cy="96" r="80" fill="transparent" stroke="#F2CDAC" strokeWidth="32" strokeDasharray="502" strokeDashoffset="490"></circle>
          </svg>

          {/* Inner circle for the donut hole and text */}
          <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-white rounded-full w-28 h-28 flex flex-col items-center justify-center shadow-inner">
            <span className="text-3xl font-bold text-grey-900">$338</span>
            <span className="text-xs text-grey-500 mt-1">of $975 limit</span>
          </div>
        </div>

        {/* Legend */}
        <div className="flex flex-col gap-4 flex-1">
          <div className="flex items-center gap-3">
            <div className="w-1 h-[40px] bg-secondary-green rounded-full"></div>
            <div>
              <p className="text-xs text-grey-500 mb-1">Entertainment</p>
              <p className="font-bold text-grey-900">$50.00</p>
            </div>
          </div>

          <div className="flex items-center gap-3">
            <div className="w-1 h-[40px] bg-secondary-cyan rounded-full"></div>
            <div>
              <p className="text-xs text-grey-500 mb-1">Bills</p>
              <p className="font-bold text-grey-900">$750.00</p>
            </div>
          </div>

          <div className="flex items-center gap-3">
            <div className="w-1 h-[40px] bg-secondary-navy rounded-full"></div>
            <div>
              <p className="text-xs text-grey-500 mb-1">Dining Out</p>
              <p className="font-bold text-grey-900">$75.00</p>
            </div>
          </div>

          <div className="flex items-center gap-3">
            <div className="w-1 h-[40px] bg-secondary-purple rounded-full"></div>
            <div>
              <p className="text-xs text-grey-500 mb-1">Personal Care</p>
              <p className="font-bold text-grey-900">$100.00</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BudgetsSummary;
