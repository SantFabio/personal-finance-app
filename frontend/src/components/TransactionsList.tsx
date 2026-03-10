

const TransactionsList = () => {
  const transactions = [
    { name: 'Emma Richardson', avatar: '/avatars/emma.jpg', date: '19 Aug 2024', amount: 75.50 },
    { name: 'Savory Bites Bistro', avatar: '/avatars/savory.jpg', date: '19 Aug 2024', amount: -55.50 },
    { name: 'Daniel Carter', avatar: '/avatars/daniel.jpg', date: '18 Aug 2024', amount: -42.30 },
    { name: 'Sun Park', avatar: '/avatars/sun.jpg', date: '17 Aug 2024', amount: 120.00 },
    { name: 'Urban Services Hub', avatar: '/avatars/urban.jpg', date: '17 Aug 2024', amount: -65.00 },
  ];

  return (
    <div className="bg-white rounded-xl p-8 shadow-sm">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl font-bold text-grey-900">Transactions</h2>
        <button className="text-grey-500 hover:text-grey-900 text-sm flex items-center">
          View All <span className="ml-2">›</span>
        </button>
      </div>

      <div className="space-y-6">
        {transactions.map((tx, index) => (
          <div key={index} className="flex justify-between items-center pb-4 border-b border-grey-100 last:border-0 last:pb-0">
            <div className="flex items-center gap-4">
              <div className="w-10 h-10 rounded-full overflow-hidden bg-grey-300">
                {/* Placeholder for avatar images. A real app would use the avatar src */}
                <div className="w-full h-full flex items-center justify-center text-xs text-grey-500 font-bold bg-beige-100 border border-grey-100">
                  {tx.name.charAt(0)}
                </div>
              </div>
              <p className="font-bold text-grey-900 text-sm">{tx.name}</p>
            </div>
            <div className="text-right">
              <p className={`font-bold ${tx.amount > 0 ? 'text-secondary-green' : 'text-grey-900'}`}>
                {tx.amount > 0 ? '+' : ''}${Math.abs(tx.amount).toFixed(2)}
              </p>
              <p className="text-xs text-grey-500 mt-1">{tx.date}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default TransactionsList;
