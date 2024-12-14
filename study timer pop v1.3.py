from flask import Flask, request, jsonify

app = Flask(__name__)

# データ管理
user_data = {"popcorn": 0, "level": 1, "time_left": 25*60}

@app.route('/get_status', methods=['GET'])
def get_status():
    return jsonify(user_data)

@app.route('/update', methods=['POST'])
def update():
    data = request.json
    user_data['popcorn'] = data.get('popcorn', user_data['popcorn'])
    user_data['level'] = data.get('level', user_data['level'])
    user_data['time_left'] = data.get('time_left', user_data['time_left'])
    return jsonify({"status": "updated", "data": user_data})

if __name__ == '__main__':
    app.run(debug=True)
